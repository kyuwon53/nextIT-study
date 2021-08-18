from selenium import webdriver
import time

my_token='텔레그램 챗봇 key 입력'
print('start telegram chat bot')
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
import FinanceDataReader as fdr
updater = Updater(my_token, use_context=True)
def get_message(update,context):
    update.message.reply_text('got text')
    update.message.reply_text(update.message.text)

def help_command(update, context):
    update.message.reply_text('무엇을 도와 드릴까요?')

#stock.xlsx에서 해당 주식 코드 가져오기
def fn_getCode(text):
    import pandas as pd
    stock = pd.read_excel('stock.xlsx')
    row = stock[stock['Name'].str.contains(text)]
    return row['Symbol'].values[0]

# 해당 주식의 게시글의 제목과 링크 가져오기
def get_stock(update,context):
    driver = webdriver.Chrome('./chromedriver')
    driver.implicitly_wait(2)
    # text = input('종목명 : ')
    code = 'A' + str(fn_getCode(context.args[0])).zfill(6)
    driver.get('https://m.finance.daum.net/quotes/' + code + '/talks')
    div = driver.find_element_by_class_name('f_clear')
    lis = div.find_elements_by_class_name('title')
    links = div.find_elements_by_css_selector('ul > li > a ')
    for link in links:
        tit = link.find_element_by_class_name('title').text
        print(tit)
        print(link.get_attribute("href"))
        update.message.reply_text(tit + "  " + link.get_attribute("href"))
    driver.quit()

def help_command(update, context):
    update.message.reply_text('무엇을 도와 드릴까요?')



message_handler = MessageHandler(Filters.text & (~Filters.command), get_message)
updater.dispatcher.add_handler(message_handler)
# commandhandler는 첫 시작을 @ 또는 / 일 때
help_handler = CommandHandler('help',help_command)
updater.dispatcher.add_handler(help_handler)
# 주식
stock_handler = CommandHandler('stock',get_stock)
updater.dispatcher.add_handler(stock_handler)

