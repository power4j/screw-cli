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

import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2022/7/22
 * @since 1.0
 */
@Data
@ConfigurationProperties(prefix = ScrewProperties.PROP_PREFIX)
public class ScrewProperties {
	public final static String PROP_PREFIX = "screw";


	private TableSelect whiteList = new TableSelect();
	private TableSelect blackList = new TableSelect();
	private EngineProp engine = new EngineProp();
	private DocProp doc = new DocProp();

	@Data
	public static class TableSelect{
		private List<String> name = Collections.emptyList();
		private List<String> prefix = Collections.emptyList();
		private List<String> suffix = Collections.emptyList();
	}
	@Data
	public static class EngineProp {
		private EngineFileType fileType = EngineFileType.HTML;
		private EngineTemplateType templateType = EngineTemplateType.freemarker;
		private String outputDir = "./target";
		private String outputFileName = "database-doc";
	}
	@Data
	public static class DocProp {
		private String title="Database document";
		private String organization="";
		private String organizationUrl="";
		private String version = "1.0";
		private String description = "Database document";
	}
}
