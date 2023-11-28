import React from 'react';
export default function CommitComponent({ showModal, closeModal }) {
    return (
        <div className={`modal ${showModal ? 'show' : ''}`}>
        <div className="modal-content">
          {/* Your modal content goes here */}
          <p>This is your modal content.</p>
          <button onClick={closeModal}>Close Modal</button>
        </div>
      </div>
    );
}