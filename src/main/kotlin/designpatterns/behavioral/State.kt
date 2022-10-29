package designpatterns.behavioral

/**
 * State design pattern is to give a context has some states and control them by switching from one to another.
 */
class Document {

    private var defaultState: DocumentState = DocumentState.Empty("opened")

    fun accessDocument(state: DocumentState) {
        println("Document is in start state = $state")
        defaultState = when (state) { // switching sate to move to another
            is DocumentState.Empty -> DocumentState.Editing("Editing")
            is DocumentState.Editing -> DocumentState.Save("Saving")
            is DocumentState.Save -> DocumentState.Save("Close")
            is DocumentState.Close -> DocumentState.Empty("Opened")
        }
        println("Document is in now sate = $defaultState")
    }
}

sealed class DocumentState {
    data class Empty(val stateName: String) : DocumentState()
    data class Editing(val stateName: String) : DocumentState()
    data class Save(val stateName: String) : DocumentState()
    data class Close(val stateName: String) : DocumentState()
}

fun main() {
    val document = Document()
    document.accessDocument(DocumentState.Empty("Starting"))
}

