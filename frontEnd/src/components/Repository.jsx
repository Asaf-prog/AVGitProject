
import React, { useState } from 'react';
import CommitComponent from './CommitComponent.jsx';

export default function Repository({ name, index, details }) {
    const [showDetails, setShowDetails] = useState(false);
    const [showModal, setShowModal] = useState(false);


    const openModal = () => {
        setShowModal(true);
      };
    
      const closeModal = () => {
        setShowModal(false);
      };
   
    const handleButtonClick = () => {
        // Toggle the showDetails state when the button is clicked
        setShowDetails(!showDetails);
        openModal;
      };
   
    return (
        <div className="repo-container">
      <h3>{name}</h3>
      <button className="repo-button" onClick={handleButtonClick}>
        {showDetails ? 'Hide Last Commit' : 'View Last Commit'}
      </button>
      {showDetails && (
        <div className="repo-details">
         
          <CommitComponent showModal={showModal} closeModal={closeModal}/>
        </div>
      )}
    </div>
    );
}


