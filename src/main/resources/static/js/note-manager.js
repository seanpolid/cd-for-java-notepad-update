"use strict"

const noteManager = {

    tbody: document.getElementsByTagName('tbody')[0],
    addButton: document.getElementById('addButton'),
    submitButton: document.getElementById('submit'),
    closeButton: document.getElementById('close'),
    titleInput: document.getElementById('title'),
    contentInput: document.getElementById('content'),
    modal: document.getElementById('modal'),
    hideLayer: document.getElementById('hideLayer'),
    hideClass: 'hide',
    message: document.getElementById('message'),
    successClass: 'success',
    errorClass: 'error',

	init: () => {
        noteManager.hideLayer.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.addButton.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.closeButton.addEventListener('click', noteManager.toggleWindow, false);
        noteManager.submitButton.addEventListener('click', noteManager.handleSubmit, false);
	},

    toggleWindow: (event) => {
        noteManager.modal.classList.toggle(noteManager.hideClass);
        noteManager.hideLayer.classList.toggle(noteManager.hideClass);

        // Always reset message
        noteManager.message.textContent = "";
        noteManager.message.classList.remove(noteManager.successClass);
        noteManager.message.classList.remove(noteManager.errorClass);
    },

    handleSubmit: (event) => {
        event.preventDefault();
        
        const title = noteManager.titleInput.value;
        const content = noteManager.contentInput.value;

        if (noteManager.isEmpty(title) || noteManager.isEmpty(content)) {
            const message = "Title and Content cannot be empty.";
            noteManager.displayError(message);
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

    displayError: (message) => {
        noteManager.message.append(document.createTextNode(message));
        noteManager.message.classList.add(noteManager.errorClass);
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
            noteManager.toggleWindow();
            noteManager.displaySuccess();
            noteManager.addRow(note);
        })
        .catch(error => {
            const message = "An occurred while trying to save. Please try again later.";
            noteManager.displayError(message);
        });
    },

    displaySuccess: () => {
        const successMessage = document.createTextNode(
            "Your note was successfully saved!"
        );
        noteManager.message.append(successMessage);
        noteManager.message.classList.add(noteManager.successClass);
        setTimeout(() => {
            noteManager.message.classList.remove(noteManager.successClass);
            noteManager.message.textContent = "";
        }, 5000);
    },

    addRow: (note) => {
        const row = document.createElement('tr');
        const titleCell = document.createElement('td');
        const contentCell = document.createElement('td');
        const countCell = document.createElement('td');
        const numWords = note.content.split(' ').length;

        titleCell.append(document.createTextNode(note.title));
        contentCell.append(document.createTextNode(note.content));
        countCell.append(document.createTextNode(numWords));

        row.append(titleCell);
        row.append(contentCell);
        row.append(countCell);
        noteManager.tbody.append(row);
    }
};

noteManager.init();
