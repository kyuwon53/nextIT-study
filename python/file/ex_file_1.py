import os
from builtins import print

currentPath = os.getcwd()       #현재 경로 위치
print(currentPath)

# 현재 경로 변경
os.chdir('/home/pc31/PycharmProjects/pythonProject/')
print(os.getcwd())

# 해당 경로의 파일이나 리스트 가져오기
fileList=os.listdir('/home/pc31/PycharmProjects/pythonProject/')
print(fileList)
for i in fileList:
    print(i)
    if os.path.isdir(i):
        print('디렉토리 : ')
    elif os.path.isfile(i):
        print('파일 : ')

path = os.getcwd()
print(path)
imgPath = path +"/"+'img'
if not os.path.isdir(imgPath):
    os.mkdir(imgPath) #만들기
else:
    #삭제
    os.rmdir(imgPath)
