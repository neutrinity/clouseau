// Licensed under the Apache License, Version 2.0 (the "License"); you may not
// use this file except in compliance with the License. You may obtain a copy of
// the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// License for the specific language governing permissions and limitations under
// the License.

package com.cloudant.clouseau

import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy
import org.apache.commons.configuration._
import org.apache.log4j.Logger

import scalang._

object Main extends App {

  val logger = Logger.getLogger("clouseau.main")

  Thread.setDefaultUncaughtExceptionHandler(
    new Thread.UncaughtExceptionHandler {
      def uncaughtException(t: Thread, e: Throwable) {
        logger.fatal("Uncaught exception: " + e.getMessage)
        System.exit(1)
      }
    }
  )

  val argumentConfig = new BaseConfiguration()
  for (arg <- args) {
    val argPair = arg.split("=")
    if (argPair.length == 2)
      argumentConfig.setProperty(argPair(0), argPair(1))
  }

  // Load and monitor configuration file.
  val config = new CompositeConfiguration()
  config.addConfiguration(argumentConfig)
  config.addConfiguration(new SystemConfiguration())

  val fileName = if (args.length > 0 && args(0).length() > 0 ) args(0) else "clouseau.ini"
  val reloadableConfig = new HierarchicalINIConfiguration(fileName)
  reloadableConfig.setReloadingStrategy(new FileChangedReloadingStrategy)
  config.addConfiguration(reloadableConfig)

  val name = config.getString("clouseau.name", "clouseau@127.0.0.1")
  val cookie = config.getString("clouseau.cookie", "monster")
  val nodeconfig = NodeConfig(
    typeFactory = ClouseauTypeFactory,
    typeEncoder = ClouseauTypeEncoder,
    typeDecoder = ClouseauTypeDecoder)
  val node = Node(name, cookie, nodeconfig)

  ClouseauSupervisor.start(node, config)
  logger.info("Clouseau running as " + name)
}
