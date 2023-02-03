package com.company.liteflow;

import com.company.liteflow.context.ALiteFlowContext;
import com.company.liteflow.context.BLiteFlowContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LiteFlowTest {

    @Resource
    private FlowExecutor flowExecutor;

    @Test
    public void testLiteFlow(){
        ALiteFlowContext aLiteFlowContext = new ALiteFlowContext();
        aLiteFlowContext.setName("lite-flow");
        BLiteFlowContext bLiteFlowContext = new BLiteFlowContext();
        bLiteFlowContext.setId(2l);
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", null, aLiteFlowContext,bLiteFlowContext);
    }
}
