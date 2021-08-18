#-*- coding:utf-8 -*-
import cx_Oracle
from tkinter import *
from tkinter import messagebox

def selectOracle(a):
    conn = cx_Oracle.connect('java','oracle','localhost:1521/XE')
    print(conn.version)
    cur = conn.cursor()
    rows = cur.execute("""select mem_name from member 
                            where mem_name like '%' || :word || '%'""",{"word":a})
    res=[]
    for i in rows:
        print(i)
        res.append(i)
    return res

def okClick():
    name= txt.get()

    messagebox.showinfo('이름',selectOracle(name))

app = Tk()

lab = Label(app, text = '이름')
lab.grid(row=0, column=0 )
txt = Entry(app)
txt.grid(row=0, column=1)

btn = Button(app, text='ok', command=okClick)
btn.grid(row=1, column=1)

app.mainloop() #실행
