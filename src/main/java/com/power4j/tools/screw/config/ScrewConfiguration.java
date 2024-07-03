/*
 * Copyright 2021 ChenJun (power4j@outlook.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.power4j.tools.screw.config;

import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.process.ProcessConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2022/7/22
 * @since 1.0
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(ScrewProperties.class)
public class ScrewConfiguration {
	private final ScrewProperties screwProperties;

	@Bean
	EngineConfig engineConfig(){
		final ScrewProperties.EngineProp engineProp = screwProperties.getEngine();
		// @formatter:off
		return EngineConfig.builder()
				//生成文件路径
				.fileOutputDir(engineProp.getOutputDir())
				//打开目录
				.openOutputDir(false)
				//文件类型
				.fileType(engineProp.getFileType())
				//生成模板实现
				.produceType(engineProp.getTemplateType())
				//自定义文件名称
				.fileName(engineProp.getOutputFileName())
				.build();
		// @formatter:on
	}

	@Bean
	ProcessConfig processConfig(){
		final ScrewProperties.TableSelect whiteList = screwProperties.getWhiteList();
		final ScrewProperties.TableSelect blackList = screwProperties.getBlackList();
		// @formatter:off
		return ProcessConfig.builder()
				//指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
				//根据名称指定表生成
				.designatedTableName(whiteList.getName())
				//根据表前缀生成
				.designatedTablePrefix(whiteList.getPrefix())
				//根据表后缀生成
				.designatedTableSuffix(whiteList.getSuffix())
				//忽略表名
				.ignoreTableName(blackList.getName())
				//忽略表前缀
				.ignoreTablePrefix(blackList.getPrefix())
				//忽略表后缀
				.ignoreTableSuffix(blackList.getSuffix())
				.build();
		// @formatter:on
	}

}
