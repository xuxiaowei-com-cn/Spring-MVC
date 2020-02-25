/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.transport.servlet.CXFServlet;

import javax.servlet.annotation.WebServlet;

/**
 * CXF 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@WebServlet(name = "wsCxfServlet", urlPatterns = "/ws/*")
public class WsCxfServlet extends CXFServlet {

}