import Commit from "./Commit.jsx";
import  { useState } from 'react';
export default function Repo({name,index}){
    const [showNewComponent, setShowNewComponent] = useState(false);
    
    const handleRepoClick = () => {
        console.log('print');
        console.log(name);
        setShowNewComponent(true);
      };

    return (
        <li onClick={handleRepoClick}>
            
            <h3>
                {!name ? 'null' : name }
                {index && <span>Index: {index}</span>}                
    
            </h3>
            <div>
                {(showNewComponent && name !== null) ?<Commit name={name}/>: null} 
            </div>

        </li>
    );
}