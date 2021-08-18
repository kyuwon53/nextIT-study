#pip install telegram
#pip install python-telegram-bot
from selenium import webdriver
my_token='텔레그램 챗봇 key 입력 '
print('start telegram chat bot')
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
#답변함수

def fn_getCode(text):
    import pandas as pd
    stock = pd.read_excel('stock.xlsx')
    row = stock[stock['Name'].str.contains(text)]
    return row['Symbol'].values[0]


def get_stock(update, context):
    driver = webdriver.Chrome('./chromedriver')
    driver.implicitly_wait(2)
    # text = input('종목명 : ')
    text = context
    code = 'A' + str(fn_getCode(text)).zfill(6)
    driver.get('https://m.finance.daum.net/quotes/' + code + '/talks')
    div = driver.find_element_by_class_name('f_clear')
    lis = div.find_elements_by_class_name('title')
    links = div.find_elements_by_css_selector('ul > li > a ')
    return links


def get_message(update,context):

    if '/주식' in update.message.text:
        texts = update.message.text.split()

        links = get_stock(texts[1])
        for link in links:
            tit = link.find_element_by_class_name('title').text
            print(tit)
            print(link.get_attribute("href"))
            update.message.reply_text(tit + '/n' + link.get_attribute("href"))
    else:
        update.message.reply_text('got text')
        update.message.reply_text(update.message.text)

def help_command(update, context):
    update.message.reply_text('무엇을 도와 드릴까요?')
def get_photo(update, context):
    file_path='text.png'
    photo_id = update.message.photo[-1].file_id
    photo_file = context.bot.getFile(photo_id)
    photo_file.download(file_path)
    update.message.reply_text('저장함')

updater = Updater(my_token, use_context=True)
message_handler = MessageHandler(Filters.text & (~Filters.command), get_message)
updater.dispatcher.add_handler(message_handler)
# commandhandler는 첫 시작을 @ 또는 / 일 때
help_handler = CommandHandler('help',help_command)
updater.dispatcher.add_handler(help_handler)
#photo
photo_handler = MessageHandler(Filters.photo, get_photo)
updater.dispatcher.add_handler(photo_handler)
updater.start_polling()
updater.idle()
