package com.share.rules.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zzg
 * @date: 2026年11月19日  00:50
 * @version: 1.0
 */
public class DroolsHelper {

    /**
     * 规则内容 -> KieContainer 缓存，避免每次计费重复编译 DRL
     */
    private static final Map<String, KieContainer> CONTAINER_CACHE = new ConcurrentHashMap<>();

    public static KieSession loadForRule(String drlStr) {
        KieContainer kieContainer = CONTAINER_CACHE.computeIfAbsent(drlStr, DroolsHelper::buildContainer);
        return kieContainer.newKieSession();
    }

    private static KieContainer buildContainer(String drlStr) {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/" + drlStr.hashCode() + ".drl", drlStr);

        // 将KieFileSystem加入到KieBuilder
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        // 编译此时的builder中所有的规则
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }

        return kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
    }
}
