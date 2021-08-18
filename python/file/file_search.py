import os

print("-----------------------")
# filename = input('찾으시는 파일을 입력하세요 ')
def file_search(dirname):
    fileList = os.listdir(dirname)
    for i in fileList:
        filePath = os.path.join(dirname,i)
        if os.path.isdir(filePath):
            file_search(filePath)
            print(filePath)

file_search('/home/')


