from selenium import webdriver
import time
import urllib.request
from urllib.parse import quote_plus

id = 'kyunim_film'
pw = '*rhakr5748'


url = input('검색할 해시태그')
driver = webdriver.Chrome('./chromedriver') # 브라우저 열기
driver.implicitly_wait(3)                   # 브라우저 열동안 대기
driver.get('https://www.instagram.com/accounts/login/')
driver.implicitly_wait(2)
driver.find_element_by_name('username').send_keys(id) # id 값에 id 입력
driver.find_element_by_name('password').send_keys(pw) # pw 값에 pw 입력
driver.find_element_by_class_name('sqdOP.L3NKy').click()
time.sleep(2)
driver.find_element_by_xpath('//*[@id="react-root"]/section/main/div/div/div/div/button').click()
time.sleep(2)
driver.find_element_by_xpath('/html/body/div[4]/div/div/div/div[3]/button[2]').click()
driver.get('https://www.instagram.com/explore/tags/'+url+'/')
# print(driver.find_element_by_class_name('g47SY').text)
from selenium.webdriver.common.keys import Keys
import os

path = '/home/pc31/Desktop/img/'
img_folder = url
if not os.path.isdir(path+img_folder):
    os.mkdir(path+img_folder)
try:
    cnt = 10
    body = driver.find_element_by_tag_name('body')
    div = driver.find_elements_by_class_name('KL4Bh')
    pagedowns = 1
    while pagedowns < cnt:
        body.send_keys(Keys.PAGE_DOWN)
        time.sleep(1)
        pagedowns += 1
        img = driver.find_elements_by_css_selector('.KL4Bh > img')
        imgUrl = set()
        for i in img:
            imgUrl.add(i.get_attribute('src'))
            print(i.get_attribute('src'))
        for index, link in enumerate(imgUrl):
            # name= i.split('/')
            # if '.jpg' in name[-1]:
            #     print(name[-1])
            urllib.request.urlretrieve(link,path+img_folder+'/'+url+f'{index}.jpg')
except Exception as e:
    print(str(e))
driver.close()
