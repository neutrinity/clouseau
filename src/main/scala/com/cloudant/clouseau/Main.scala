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

  val KeyClouseauName = "CLOUSEAU_NAME"
  val KeyClouseauCookie = "CLOUSEAU_COOKIE"
  val KeyClouseauDir = "CLOUSEAU_DIR"
  val KeyClouseauDirClass = "CLOUSEAU_DIR_CLASS"
  val KeyClouseauLockClass = "CLOUSEAU_LOCK_CLASS"
  val KeyClouseauMaxIndexesOpen = "CLOUSEAU_MAX_INDEXES_OPEN"

  Thread.setDefaultUncaughtExceptionHandler(
    new Thread.UncaughtExceptionHandler {
      def uncaughtException(t: Thread, e: Throwable) {
        logger.fatal("Uncaught exception: " + e.getMessage)
        System.exit(1)
      }
    }
  )

  // Load and monitor configuration file.
  val config = new CompositeConfiguration()
  config.addConfiguration(new SystemConfiguration())
  config.addConfiguration(new EnvironmentConfiguration())

  val fileName = if (args.length > 0) args(0) else "clouseau.ini"
  val reloadableConfig = new HierarchicalINIConfiguration(fileName)
  reloadableConfig.setReloadingStrategy(new FileChangedReloadingStrategy)
  config.addConfiguration(reloadableConfig)

  val name = config.getString(KeyClouseauName, "clouseau@127.0.0.1")
  val cookie = config.getString(KeyClouseauCookie, "monster")
  val nodeconfig = NodeConfig(
    typeFactory = ClouseauTypeFactory,
    typeEncoder = ClouseauTypeEncoder,
    typeDecoder = ClouseauTypeDecoder)
  val node = Node(name, cookie, nodeconfig)

  ClouseauSupervisor.start(node, config)
  logger.info("Clouseau running as " + name)
}
