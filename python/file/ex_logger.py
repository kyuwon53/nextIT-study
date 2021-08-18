import mylogger

log = mylogger.make_logger()
log.debug('test')
log.info('test')
log.warning('test')

def cal(a,b):
    try:
        return  a/b
    except ZeroDivisionError:
        log.exception("Zero division")
    else:
        return result
cal(2,0)