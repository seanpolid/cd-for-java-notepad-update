"use strict"

const noteManager = {

    addButton: document.getElementById('addButton'),
    submitButton: document.getElementById('submit'),
    closeButton: document.getElementById('close'),
    titleInput: document.getElementById('title'),
    contentInput: document.getElementById('content'),
    modal: document.getElementById('modal'),
    hideLayer: document.getElementById('hideLayer'),
    hideClass: 'hide',

	init: () => {
        noteManager.hideLayer.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.addButton.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.closeButton.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.submitButton.addEventListener('click', noteManager.handleSubmit, false);
	},

    toggleWindow: (event) => {
        noteManager.modal.classList.toggle(noteManager.hideClass);
        noteManager.hideLayer.classList.toggle(noteManager.hideClass);
    },

    handleSubmit: (event) => {
        event.preventDefault();
        
        const title = noteManager.titleInput.value;
        const content = noteManager.contentInput.value;

        if (noteManager.isEmpty(title) || noteManager.isEmpty(content)) {
            noteManager.displayError();
        } else {
            const note = {
                title: title,
                content: content
            };
            noteManager.postNote(note);
            noteManager.titleInput.value = "";
            noteManager.contentInput.value = "";
        }
    },

    isEmpty: (string) => {
        const pattern = /\w/;
        return !pattern.test(string);
    },

    postNote: (note) => {
        fetch("/notes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(note)
        })
        .then(response => {
            noteManager.displaySuccess();
        })
        .catch(error => {
            console.log(error);
        });
    },

    displaySuccess: () => {

    }
};

noteManager.init();
