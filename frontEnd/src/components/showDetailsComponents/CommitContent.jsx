import React, { useState } from 'react';
import ShowGitFile from './ShowGitFile.jsx';
import ShowChildren from './ShowChildren.jsx';

export default function commitContent({item,userPassword}){
    const [showDetails, setShowDetails] = useState(false);
    
     function handleShowMore() {
        setShowDetails(!showDetails);
        console.log("here");
        console.log(item.gitTreeNodeDTO.gitFileDTOList);
    }

    return (
        <div style={{ textAlign: 'left' }}>
        
            <h3><strong>Commit Details:</strong></h3>
            <h3><strong>The Root Directory:</strong> {item.root}</h3>
            <h3><strong>The Last Commit:</strong> {item.lastSh1}</h3>
            <h3><strong>Comment:</strong> {item.comment}</h3>
            <h3><strong>Author:</strong> {item.author}</h3>
            <h3><strong>Time:</strong> {item.time}</h3>
            
            <button onClick={handleShowMore}>Show more</button>
            {showDetails?<ShowGitFile treeNode={item.gitTreeNodeDTO.gitFileDTOList}/> :null}
            {showDetails? <ShowChildren treeNode={item.gitTreeNodeDTO}/> :null}
            
            
        </div>

    );
}

