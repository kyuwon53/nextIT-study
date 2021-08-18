from tkinter import *

class moveBall:
    def __init__(self,window):
        self.canvas = Canvas(window, width=400, height=300)
        self.canvas.pack()
        self.window = window
        self.deltax = 2
        self.deltay = 3
        self.canvas.create_oval(100,150,150,200, fill='red' , tag='redBall')
        self.canvas.pack()
    def move_left(self, event):
        self.canvas.move('redBall',-self.deltax,0)
        self.canvas.after(20)
        self.canvas.update()

    def move_right(self, event):
        self.canvas.move('redBall', self.deltax, 0)
        self.canvas.after(20)
        self.canvas.update()

    def move_up(self, event):
        self.canvas.move('redBall',0, -self.deltay)
        self.canvas.after(20)
        self.canvas.update()

    def move_down(self, event):
        self.canvas.move('redBall',0, self.deltay)
        self.canvas.after(20)
        self.canvas.update()
window = Tk()
mov = moveBall(window)
mov.canvas.bind('<Left>',mov.move_left)
mov.canvas.bind('<Right>',mov.move_right)
mov.canvas.bind('<Up>',mov.move_up)
mov.canvas.bind('<Down>',mov.move_down)
mov.canvas.focus_set()
mov.canvas.pack()
window.mainloop()
