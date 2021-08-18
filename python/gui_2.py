from tkinter import *

def onPush(event):
    print('키보드 입력: ' , event.keycode)
def onClick(event):
    print('마우스 클릭: ' , event.x, event.y)

app = Tk()
frame = Frame(app, width=200, height=100)
frame.bind('<Key>',onPush)
frame.bind('<Button-1>',onClick)
frame.focus_set()
frame.pack()
app.mainloop()
