package next.address.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import next.address.MainApp;
import next.address.model.Person;
import next.address.util.DateUtil;

public class PersonOverviewController {
	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // 메인 애플리케이션 참조
    private MainApp mainApp;

    /**
     * 생성자.
     * initialize() 메서드 이전에 호출된다.
     */
    public PersonOverviewController() {
    }

    /**
     * 컨트롤러 클래스를 초기화한다.
     * fxml 파일이 로드되고 나서 자동으로 호출된다.
     */
    @FXML
    private void initialize() {
    	// 연락처 테이블의 두 열을 초기화한다.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        // 연락처 정보를 지운다.
        showPersonDetails(null);

        // 선택을 감지하고 그 때마다 연락처의 자세한 정보를 보여준다.
//        personTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        
        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
        	@Override
        	public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
        		System.out.println("oldValue = " + oldValue);
        		System.out.println("newValue = " + newValue);
        		showPersonDetails(newValue);
        	}
		});
        
    }

    /**
     * 참조를 다시 유지하기 위해 메인 애플리케이션이 호출한다.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // 테이블에 observable 리스트 데이터를 추가한다.
        personTable.setItems(mainApp.getPersonData());
    }
    /**
     * 연락처의 자세한 정보를 보여주기 위해 모든 텍스트 필드를 채운다.
     * 만일 person이 null이면 모든 텍스트 필드가 지워진다.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // person 객체로 label에 정보를 채운다.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthday()));

        } else {
            // person이 null이면 모든 텍스트를 지운다.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    /**
     * 사용자가 삭제 버튼을 클릭하면 호출된다.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // 아무것도 선택하지 않았다.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("선택된 회원 없음");
            alert.setContentText("삭제할 회원을 선택 해주세요. ");

            alert.showAndWait();
        }
    }
    /**
     * 사용자가 new 버튼을 클릭할 때 호출된다.
     * 새로운 연락처 정보를 넣기 위해 다이얼로그를 연다.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * 사용자가 edit 버튼을 클릭할 때 호출된다.
     * 선택한 연락처 정보를 변경하기 위해 다이얼로그를 연다.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // 아무것도 선택하지 않았다.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
