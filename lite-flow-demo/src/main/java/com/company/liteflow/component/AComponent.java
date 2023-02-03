package com.company.liteflow.component;


import com.company.liteflow.context.ALiteFlowContext;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("a")
@Slf4j
public class AComponent extends NodeComponent {
    @Override
    public void process() throws Exception {
        ALiteFlowContext aLiteFlowContext = this.getContextBean(ALiteFlowContext.class);
        log.info(aLiteFlowContext.getName()+" a component");
    }
}
