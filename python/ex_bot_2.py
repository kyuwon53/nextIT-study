#pip install telegram
#pip install python-telegram-bot

my_token='텔레그램 챗봇 key'
print('start telegram chat bot')
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
#답변함수
def get_message(update,context):
    update.message.reply_text('got text')
    update.message.reply_text(update.message.text)

def help_command(update, context):
    update.message.reply_text('무엇을 도와 드릴까요?')

#사진 저장
def get_photo(update, context):
    file_path='text.png'
    photo_id = update.message.photo[-1].file_id
    photo_file = context.bot.getFile(photo_id)
    photo_file.download(file_path)
    update.message.reply_text('저장함')

#스크린샷
def fn_image():
    from selenium import webdriver
    import time
    import util
    filename = '코로나19.png'
    wd = webdriver.Chrome('./chromedriver')
    url = 'http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun='
    wd.get(url)
    util.fullpage_screenshot(wd, filename)
    time.sleep(3)
    wd.quit()
    return filename

def get_corona(update, context):
    user_id = update.effective_chat.id
    fileName = fn_image()
    context.bot.send_photo(user_id, open(fileName, 'rb'))

# 핸들러 등록
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
# 코로나
corona_handler = CommandHandler('covid',get_corona)
updater.dispatcher.add_handler(corona_handler)
updater.start_polling()
updater.idle()
