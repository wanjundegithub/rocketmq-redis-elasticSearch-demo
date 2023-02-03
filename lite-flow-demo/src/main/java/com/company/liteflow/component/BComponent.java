package com.company.liteflow.component;

import com.company.liteflow.context.ALiteFlowContext;
import com.company.liteflow.context.BLiteFlowContext;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("b")
@Slf4j
public class BComponent extends NodeComponent {
    @Override
    public void process() throws Exception {
        ALiteFlowContext aLiteFlowContext = this.getContextBean(ALiteFlowContext.class);
        BLiteFlowContext bLiteFlowContext = this.getContextBean(BLiteFlowContext.class);
        log.info(aLiteFlowContext.getName()+" b component "+bLiteFlowContext.getId());
    }
}
