package com.example.aop;

import com.example.base.exception.BizException;
import com.example.helper.WechatHelper;
import com.example.mapper.PublicBaseInfoEntityMapper;
import com.example.model.PublicBaseInfoEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/20 10:39
 */
@Aspect
@Component
public class WechatServiceAspect {

    private static final Logger log = LoggerFactory.getLogger(WechatServiceAspect.class);

    @Autowired
    private PublicBaseInfoEntityMapper publicBaseInfoMapper;

    /**
     * Pointcut定义切点
     * public修饰符的   返回值任意  com.cy.controller包下面的任意类的任意方法任意参数
     */
    @Pointcut("execution(public * com.example.service.impl.WechatServiceImpl.*(..))&& @annotation(com.example.aop.annotation.WechatServiceAopAnnotation)")
    public void anyMethod(){
    }

    @Before("anyMethod()")
    public void doBefore(JoinPoint joinPoint) throws BizException {
        log.info("WechatServiceImpl方法执行前...");
        ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = sra.getRequest();
        Long projectId = (Long)joinPoint.getArgs()[0];
        PublicBaseInfoEntity publicBaseInfoEntity = publicBaseInfoMapper.selectByPrimaryKey(projectId);
        if(StringUtils.isEmpty(publicBaseInfoEntity.getAccessToken())|| publicBaseInfoEntity.getExpireTime().before(new Date())){
            log.info("access_token不可用");
            String accessToken = WechatHelper.getWechatToken(publicBaseInfoEntity.getAppid(),publicBaseInfoEntity.getSecret());
            publicBaseInfoEntity.setAccessToken(accessToken);
            publicBaseInfoEntity.setExpireTime(new Date(System.currentTimeMillis()+7200*1000));
            publicBaseInfoMapper.updateByPrimaryKeySelective(publicBaseInfoEntity);
        }
//        log.info("url: " + request.getRequestURI());//url
//        log.info("ip: " + request.getRemoteHost());//ip
//        log.info("method: "+request.getMethod());//post or get? or ?
//        log.info("class.method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @After("anyMethod()")
    public void doAfter(JoinPoint joinPoint){
//        log.info("方法执行后...");
    }

    @AfterReturning(returning="result", pointcut="anyMethod()")
    public void doAfterReturnint(Object result){
        log.info("方法返回值：" + result);
    }

    @AfterThrowing(throwing="ex",pointcut="anyMethod()")
    public void doRecoveryActions(BizException ex){
        System.out.println("目标方法中抛出的异常:"+ex.getCode());
    }
}
