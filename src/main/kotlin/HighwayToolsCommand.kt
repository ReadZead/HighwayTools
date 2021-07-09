import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper.sendChatMessage
import trombone.IO.printSettings
import trombone.Pathfinder.distancePending

object HighwayToolsCommand : ClientCommand(
    name = "highwaytools",
    alias = arrayOf("ht", "hwt", "high"),
    description = "Customize settings of HighwayTools."
) {

    init {
        literal("add", "new", "+") {
            block("block") { blockArg ->
                execute("Add a block to ignore list") {
                    val added = HighwayTools.ignoreBlocks.add(blockArg.value.registryName.toString())
                    if (added) {
                        printSettings()
                        sendChatMessage("Added &7${blockArg.value.localizedName}&r to ignore list.")
                    } else {
                        sendChatMessage("&7${blockArg.value.localizedName}&r is already ignored.")
                    }
                }
            }
        }

        literal("del", "rem", "-") {
            block("block") { blockArg ->
                execute("Remove a block from ignore list") {
                    val removed = HighwayTools.ignoreBlocks.remove(blockArg.value.registryName.toString())
                    if (removed) {
                        printSettings()
                        sendChatMessage("Removed &7${blockArg.value.localizedName}&r from ignore list.")
                    } else {
                        sendChatMessage("&7${blockArg.value.localizedName}&r is not yet ignored.")
                    }
                }
            }
        }

//        literal("from", "start") {
//            blockPos("position") { blockPosArg ->
//                execute("Sets starting coordinates") {
//                    // ToDo: Make starting position for next instance
////                    HighwayTools.startingPos = blockPosArg.value
//                }
//            }
//        }

//        literal("to", "stop") {
//            blockPos("position") { blockPosArg ->
//                execute("Sets stopping coordinates and starts bot") {
//                    if (HighwayTools.isEnabled) {
//                        sendChatMessage("Run this command when the bot is not running")
//                    } else {
//                        HighwayTools.targetBlockPos = blockPosArg.value
//                        sendChatMessage("Started HighwayTools with target @(${blockPosArg.value.asString()})")
//                        HighwayTools.enable()
//                    }
//                }
//            }
//        }

        literal("distance") {
            int("distance") { distanceArg ->
                execute("Set the target distance until the bot stops") {
                    distancePending = distanceArg.value
                    sendChatMessage("HighwayTools will stop after (${distanceArg.value}) blocks distance. To remove the limit use distance 0")
                }
            }
        }

        literal("material", "mat") {
            block("block") { blockArg ->
                execute("Set a block as main material") {
                    HighwayTools.material = blockArg.value
                    sendChatMessage("Set your building material to &7${blockArg.value.localizedName}&r.")
                }
            }
        }

        literal("material", "mat") {
            block("block") { blockArg ->
                execute("Set a block as main material") {
                    HighwayTools.material = blockArg.value
                    sendChatMessage("Set your building material to &7${blockArg.value.localizedName}&r.")
                }
            }
        }

        literal("food", "fd") {
            item("item") { itemArg ->
                execute("Set a block as filler material") {
                    HighwayTools.food = itemArg.value
                    sendChatMessage("Set your food item to &7${itemArg.value.registryName}&r.")
                }
            }
        }

        execute("Print the settings") {
            printSettings()
        }
    }
}