import urllib.request
from selenium import webdriver
import time
import cx_Oracle
from selenium.webdriver.common.keys import Keys
conn = cx_Oracle.connect('{접속명}','{비번}','{접속호스트:포트/SID}')
cur = conn.cursor()
index =1;
driver = webdriver.Chrome('./chromedriver')
driver.get('https://www.klac.or.kr/legalinfo/counsel.do')
elem=driver.find_element_by_xpath('//*[@id="openList"]/li[2]/a')
driver.execute_script("arguments[0].click();", elem)
time.sleep(1)
list = driver.find_element_by_xpath('//*[@id="content"]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[2]/a')
driver.execute_script("arguments[0].click();", list)
for i in range(400):
    category = driver.find_element_by_css_selector('#print_page > div:nth-child(1) > dl > dd')
    title = driver.find_element_by_css_selector('#print_page > div:nth-child(2) > dl > dd')
    question =driver.find_element_by_css_selector('#print_page > div:nth-child(3) > dl > dd')
    answer =driver.find_element_by_css_selector('#print_page > div:nth-child(4) > dl > dd')
    cate = category.text.split('>')
    cnt =cur.execute("""INSERT INTO faq (     bo_no
                                            , faq_cd
                                            , mem_id
                                            , bo_title
                                            , bo_que
                                            , bo_content
                                            , bo_hit
                                        ) VALUES (
        FAQ_SEQ.nextval+221
        ,DECODE( :v1,' 소액체당금','FAQ11',' 해고','FAQ12',' 근로계약','FAQ13',' 최우선변제권','FAQ14',' 임금 및 퇴직금','FAQ15',' 근로일반','FAQ16','근로시간','FAQ17')
        ,'admin'
        , :v2
        , :v3
        , :v4
        , 0
    ) """, {"v1":cate[1],"v2":title.text,"v3":question.text,"v4":answer.text})
    conn.commit()
    print('구분 : ' + cate[1])
    print('제목 : ' + title.text)
    # print('질문 : ' + question.text)
    # print('답변 : ' + answer.text)
    next = driver.find_element_by_xpath('//*[@id="content"]/div[2]/div/form/div[3]/dl[2]/dd/span/a')
    driver.execute_script("arguments[0].click();", next)
    time.sleep(1)
cur.close()
conn.close()
driver.close()

