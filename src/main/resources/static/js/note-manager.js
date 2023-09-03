"use strict"

const noteManager = {

    submitButton: document.getElementById('submit'),
    titleInput: document.getElementById('title'),
    contentInput: document.getElementById('content'),

	init: () => {
        noteManager.submitButton.addEventListener('click', noteManager.handleSubmit, false);
	},

    handleSubmit: (event) => {
        event.preventDefault();
        
        const title = noteManager.titleInput.value;
        const content = noteManager.contentInput.value;

        const note = new Note(title, content);
        const noteString = JSON.stringify(note);
        console.log(noteString);
        fetch("/notes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: noteString
        })
        .then(response => {
            console.log("success!", response.body);
        })
        .catch(error => {
            console.log("error:", error);
        });
    }
}

class Note {
    title;
    content;

    constructor(title, content) {
        this.title = title;
        this.content = content;
    }
}

noteManager.init();
