import logging
from time import localtime,strftime
# DEBUG     : 간단히 문제를 진단하고 싶을 때 필요한 정보를 기록
# INFO      : 계획대로 작동하고 있음을 알리는 확인 메세지
# WARNING   : 소프트웨어가 작동은 하고 있지만, 예상치 못한 일이 발생하고 있다고 알림
# ERROR     : 중대한 문제로 소프트웨어 몇몇 기능을 수행하지 못함
# CRITICAL  : 작동이 불가능한 수준의 심각한 에러 알림

def make_logger(name= None):
    # logger instance 만들기
    logger = logging.getLogger(name)
    # logger level 설정
    logger.setLevel(logging.DEBUG)
    # formatter 지정
    formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")
    # handler instance 생성
    console = logging.StreamHandler()
    file_handler = logging.FileHandler(filename= strftime("%Y%m%d",localtime())+'.log')

    console.setLevel(logging.INFO)
    file_handler.setLevel(logging.DEBUG)

    console.setFormatter(formatter)
    file_handler.setFormatter(formatter)

    logger.addHandler(console)
    logger.addHandler(file_handler)

    return logger