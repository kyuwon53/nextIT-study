package next.address.member.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import next.address.member.dao.MemberDao;
import next.address.member.model.MemberVO;
import next.address.util.DateUtil;

public class MemberListController implements Initializable {
	//제어가 필요한 컨트롤러 명칭 -> fx:id
	@FXML private TextField txtMemId;
	@FXML private TextField txtMemPass;
	@FXML private TextField txtMemName;
	@FXML private TextField txtMemBir;
	@FXML private TextField txtMemHp;
	@FXML private TextField txtMemMail;
	@FXML private TextField txtMemJob;
	@FXML private TextField txtMemMileage;
	
	@FXML private Button btnNew;
	@FXML private Button btnEdit;
	@FXML private Button btnSave;
	@FXML private Button btnCancel;
	
	@FXML private TableView<MemberVO> memberTable;
	@FXML private TableColumn<MemberVO, String> columnMemId;
	@FXML private TableColumn<MemberVO, String> columnMemName;
	@FXML private TableColumn<MemberVO, String> columnMemJob;
	
	private MemberDao dao = null;
	private Mode currentMode = Mode.VIEW_MODE;
	private ObservableList<MemberVO> memberList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new MemberDao();
		List<MemberVO> t = dao.getMemberList();
		memberList.addAll(t);
		
		TableColumn<MemberVO, String> columnMemId = (TableColumn<MemberVO, String>)memberTable.getColumns().get(0);
		TableColumn<MemberVO, String> columnMemName = (TableColumn<MemberVO, String>)memberTable.getColumns().get(1);
		TableColumn<MemberVO, String> columnMemJob = (TableColumn<MemberVO, String>)memberTable.getColumns().get(2);
		
		columnMemId.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("memId"));
		columnMemName.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("memName"));
		columnMemJob.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("memJob"));
		
		
		memberTable.setItems(memberList);
		memberTable.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue)
																					-> { changeInputMode(Mode.VIEW_MODE);
																						viewMember(newValue);
																						});
		
		btnNew.setOnAction(e -> handleBtnNew(e));
		btnEdit.setOnAction(e -> handleBtnEdit(e));
		btnSave.setOnAction(e -> handleBtnSave(e));
		btnCancel.setOnAction(e -> handleBtnCancel(e));
		
		
	}// initialize end
	
	private void handleBtnNew(ActionEvent e) {
		viewMember(null);
		currentMode = Mode.NEW_MODE;
		changeInputMode(Mode.NEW_MODE);
		changeButtonMode(true);
	}
	private void handleBtnEdit(ActionEvent e) {
		currentMode = Mode.EDIT_MODE;
		changeInputMode(Mode.EDIT_MODE);
		changeButtonMode(true);
	}
	private void handleBtnSave(ActionEvent e) {
		if (!isInputValid()) { //  검증에 실패하면 false 반환
			return;
		}
		MemberVO member = new MemberVO();
		member.setMemId(txtMemId.getText());
		member.setMemPass(txtMemPass.getText());
		member.setMemName(txtMemName.getText());
		member.setMemBir(txtMemBir.getText());
		member.setMemHp(txtMemHp.getText());
		member.setMemMail(txtMemMail.getText());
		member.setMemJob(txtMemJob.getText());
		member.setMemMileage(Integer.parseInt(txtMemMileage.getText()));
		
		if(currentMode == Mode.NEW_MODE) {
		// 신규 저장 : DB에 해당 회원이 존재하는지 확인 
		// 존재: 해당 아이디가 존재합니다. 
		// 존재X : DB에 저장 (insert)
		String id = txtMemId.getText();
		MemberVO m = dao.getMember(id);
		if(m != null) {
			msgbox("아이디 중복", "해당 아이디는 이미 사용중입니다. 다른 아이디 사용하세요");
			return;
		}
		dao.insertMember(member);
		// DB에 저장후 테이블에 모델에 추가 
		// memberTable.getSelectionModel().getSelectedItems().add(member); 에러발생
		memberList.add(member);
		msgbox("가입완료","가입이 완료되었습니다.");
		}else {// 수정 // DB에 저장(update)
			boolean result = dao.updateMember(member);
			
			if(result) {
				// DB에 저장후 테이블에 모델에 추가
				MemberVO orig = memberTable.getSelectionModel().getSelectedItem();
				// clone 유틸: 리플렉션을 사용해서 공통화 
				// BeanUtils.copyProperties(member, orig); => class간의 property 복사 
				// VO :  javaFx property 형으로 정의 (기본형으로는 화면과 동기화에 버그)
				orig.setMemId(member.getMemId());
				orig.setMemPass(member.getMemPass());
				orig.setMemName(member.getMemName());
				orig.setMemBir(member.getMemBir());
				orig.setMemHp(member.getMemHp());
				orig.setMemMail(member.getMemMail());
				orig.setMemJob(member.getMemJob());
				orig.setMemMileage(member.getMemMileage());
				msgbox("수정완료", "회원내용이 변경되었습니다.");
			}else {
				msgbox("수정실패", "회원아이디 또는 비밀번호를 확인해주세요 ");
				return;
			}
		}
		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	} // handleBtnSave
	
	private void handleBtnCancel(ActionEvent e) {
		
		MemberVO currentMember = memberTable.getSelectionModel().getSelectedItem();
		viewMember(currentMember);
		
		changeInputMode(Mode.VIEW_MODE);
		changeButtonMode(false);
	}
	
	private void viewMember(MemberVO member) {
		if(member != null) {
			txtMemId.setText(member.getMemId());
			txtMemPass.setText(member.getMemPass());
			txtMemName.setText(member.getMemName());
			txtMemBir.setText(member.getMemBir());
			txtMemHp.setText(member.getMemHp());
			txtMemMail.setText(member.getMemMail());
			txtMemJob.setText(member.getMemJob());
			txtMemMileage.setText(""+member.getMemMileage());
		}else {
			txtMemId.setText("");
			txtMemPass.setText("");
			txtMemName.setText("");
			txtMemBir.setText("");
			txtMemHp.setText("");
			txtMemMail.setText("");
			txtMemJob.setText("");
			txtMemMileage.setText("");
		}
			
	}
	
	private void changeInputMode(Mode changeMode) {
		txtMemPass.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemName.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemBir.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemHp.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemMail.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemJob.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		txtMemMileage.setEditable(changeMode == Mode.VIEW_MODE ? false : true);
		
		if(changeMode == Mode.NEW_MODE) {
			txtMemId.setEditable(true);
		}else {
			txtMemId.setEditable(false);
		}
	}
	
	public void changeButtonMode(boolean modify) {
		// modify : false 신규, 변경  활성화 | true 저장, 취소 활성화 
		btnNew.setDisable(modify);
		btnEdit.setDisable(modify);
		btnSave.setDisable(!modify);
		btnCancel.setDisable(!modify);
	}
	private boolean isInputValid() {
        String errorMessage = "";

        if (StringUtils.isBlank(txtMemId.getText())) {
            errorMessage += "아이디는 필수입니다.\n";
        }
        if (txtMemId.getText() != null && !txtMemId.getText().matches("^\\w{4,16}$")) {
            errorMessage += "아이디는 알파벳, 숫자, 언더바로 4글자 이상 16글자 이하입니다.\n";
        }
        if (StringUtils.isBlank(txtMemPass.getText())) {
            errorMessage += "패스워드는 필수입니다.\n";
        }
        if (txtMemPass.getText() != null && !txtMemPass.getText().matches("^\\w{4,16}$")) {
            errorMessage += "패스워드는 알파벳, 숫자, 언더바로 4글자 이상 16글자 이하입니다.\n";
        }
        if (StringUtils.isBlank(txtMemName.getText())) {
            errorMessage += "회원명은 필수입니다.\n";
        }
        if (StringUtils.isBlank(txtMemMail.getText())) {
            errorMessage += "메일은 필수입니다.\n";
        }
        
        // 마일리지 : 입력시 숫자 
        // 생일 : 입력시 날짜형식 
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // 오류 메시지를 보여준다.
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("유효성 검증 실패");
//            alert.setHeaderText("아래의 내용을 확인해 주세요");
//            alert.setContentText(errorMessage);
//            alert.showAndWait();
        	msgbox("유효성 검증 실패" ,"아래의 내용을 확인해 주세요", errorMessage, AlertType.ERROR);
            return false;
        }
    }
	
	private void msgbox(String title, String content) {
		msgbox(title, null, content, AlertType.INFORMATION);
	}

	private void msgbox(String title, String header, String content) {
		msgbox(title, header, content, AlertType.INFORMATION);
	}

	private void msgbox(String title, String header, String content, AlertType alertType) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
	}
	

}// class

enum Mode{
	NEW_MODE, EDIT_MODE, VIEW_MODE 
}