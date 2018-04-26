package com.concurrency;

import com.concurrency.annotation.Immutable;
import com.concurrency.annotation.ThreadSafe;
import org.junit.runner.notification.RunListener;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by 王宁 on 2017/12/29.
 */
@ThreadSafe
public class VolatileCachedFactorizer{
    private volatile OneValueCache cache = new OneValueCache(null,null);

    public void service(ServletRequest req, ServletResponse res){

        BigInteger i = BigInteger.valueOf(Long.parseLong(req.getParameter("")));
        BigInteger[] factors = cache.getFactors(i);
        if (factors==null){
            factors=factor(i);
            OneValueCache cache = new OneValueCache(i, factors);
        }
        //....
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }
}

/**
 * 不可变对象的规则
 * 1.对象创建以后其状态就不能改变
 * 2.对象的所有域都是final类型
 * 3.对象是正确创建的(在对象创建期间, this引用没有逸出)
 */

@Immutable
class OneValueCache{
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;
    public OneValueCache(BigInteger i,BigInteger[] factors){
        lastNumber=i;
        lastFactors= Arrays.copyOf(factors,factors.length);
    }
    public BigInteger[] getFactors(BigInteger i){
        if (lastNumber==null||!lastNumber.equals(i)){
            return null;
        }else {
            return Arrays.copyOf(lastFactors,lastFactors.length);
        }
    }
}
