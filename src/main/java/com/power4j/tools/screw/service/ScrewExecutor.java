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

package com.power4j.tools.screw.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.power4j.tools.screw.config.ScrewProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2022/7/22
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ScrewExecutor {
	private final EngineConfig engineConfig;
	private final ProcessConfig processConfig;
	private final DataSource dataSource;

	public void genDoc(ScrewProperties.DocProp docProp){
		// @formatter:off
		Configuration config = Configuration.builder()
				.version(docProp.getVersion())
				.description(docProp.getDescription())
				.title(docProp.getTitle())
				.organization(docProp.getOrganization())
				.organizationUrl(docProp.getOrganizationUrl())
				.dataSource(dataSource)
				.engineConfig(engineConfig)
				.produceConfig(processConfig)
				.build();
		// @formatter:on
		new DocumentationExecute(config).execute();
	}
}
