import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import CommitComponent from './CommitComponent.jsx';

export default function Repository({ name,userPassword,path }) {
    const [showDetails, setShowDetails] = useState(false);
  const [showNewComponent, setShowNewComponent] = useState(false);

  const handleRepoClick = () => {
    setShowNewComponent(true);
    setShowDetails(!showDetails);
    console.log(`Clicked on repository ${name}`);
  };

  return (
    <div className="repo-container">
      <button className='beautiful-button' onClick={handleRepoClick}>{name}</button>
      {showDetails && showNewComponent && (
        <div><CommitComponent
        name = {name}
         userPassword={userPassword}
         path={path}
         /></div>
      )}
    </div>
  );
}
