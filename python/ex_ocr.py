import pytesseract
import re
my_token='1850785379:AAGjB7vfPP8hxlf0CagfWZHTpkFPG1GIMO0'
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
import FinanceDataReader as fdr
updater = Updater(my_token, use_context=True)
def get_photo(update, context):
    file_path='text.png'
    photo_id = update.message.photo[-1].file_id
    photo_file = context.bot.getFile(photo_id)
    photo_file.download(file_path)
    phototext = pytesseract.image_to_string('text.png',lang='eng+kor')
    # phototext = phototext.replace('\n\n','')
    # transtext = re.sub('\n\n','\n',phototext)
    print(phototext)
    update.message.reply_text(phototext)

#photo
photo_handler = MessageHandler(Filters.photo, get_photo)
updater.dispatcher.add_handler(photo_handler)
updater.start_polling()
updater.idle()
# print(pytesseract.image_to_string('text.png',lang='kor'))
