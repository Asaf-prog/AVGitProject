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

 // const openNewWindow = () => {
  //   // Open a new window with the CommitComponent
  //   const newWindow = window.open('', '_blank', 'width=600,height=400,resizable=yes,scrollbars=yes');
  //   newWindow.document.write('<html><head><title>New Page</title></head><body>');
  //   newWindow.document.write('<div id="root"></div>');
  //   newWindow.document.write('</body></html>');
  //   newWindow.document.getElementById('root').innerHTML = '<h1>Loading...</h1>';

  //   // Render the CommitComponent inside the new window
  //   ReactDOM.render(<CommitComponent />, newWindow.document.getElementById('root'));
  // };