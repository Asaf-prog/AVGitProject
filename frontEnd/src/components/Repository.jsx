
import React, { useState } from 'react';
import CommitComponent from './CommitComponent.jsx';

export default function Repository({ name }) {
    const [showDetails, setShowDetails] = useState(false);
    const [showNewComponent, setShowNewComponent] = useState(false);


    const handleRepoClick = () => {
        setShowNewComponent(true);
        setShowDetails(!showDetails);
        console.log(`Clicked on repository ${name} `);
      };
   

    return (
        
        <div className="repo-container" >
      <button className='repo-button '  onClick={handleRepoClick}>{name}</button>
      {showDetails && (
        <div>
          {showNewComponent ?
          <CommitComponent 
          name={name}
          /> : null}
        </div>
      )}
    </div>
    );
}


