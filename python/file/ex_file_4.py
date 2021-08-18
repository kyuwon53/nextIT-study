# pip install apscheduler
# python 스크립트를 주기적으로 실행할 수 있게 해주는 스케줄링 라이브러리
# 예약 방식
# date: 단 한번 특정 시점에 실행
# interval : 특정 주기로 연속 실행
# cron : crontab 형식으로 예약할 때 사용
from apscheduler.schedulers.blocking import  BlockingScheduler
import datetime

def exec_interval():
    print('interval')
    print(datetime.datetime.now())

def exec_cron_day():
    print('cron')
    print(datetime.datetime.now())

def job(*args):
    for i in args:
        print(i)
    print('job')
    list = sched.get_jobs()
    print(list)
    sched.remove_job('test_1')

sched = BlockingScheduler()
# 10초마다
sched.add_job(exec_interval,'interval', seconds=10)
sched.add_job(job,'interval',seconds=10, id='test_1', args=[1,2,3])
sched.add_job(exec_cron_day,'cron', hour='15',minute='05')
sched.start()