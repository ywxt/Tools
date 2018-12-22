package ywxt.tools.autoreply.models

data class MessageModel (var message:String,var position: Position)

enum class Position{
    LEFT,
    RIGHT,
}