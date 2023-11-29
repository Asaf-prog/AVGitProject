import  { useState, useEffect } from 'react';
import CommitContent from './CommitContent.jsx';
export default function CommitComponent({name}) {
    
    const [data, setData] = useState([]);
    const [click, setClick] = useState(false);
    
    const handleClick = () => {
        setClick(!click);
      };


    useEffect(() => {
        
        async function fetchData() {
        
            try {   
            const url = "http://localhost:8080/AvGit/commit";
      
            const response = await fetch(url);
      
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
      
            const responseData = await response.json();
            console.log('Response from http://localhost:8080/AvGit/commit', responseData);
            setData(responseData);
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        }
        fetchData();
      }, []);
    
    return (
        <div >
            {data.length === 0 ? null : data.map((item,index) => (
                <div>
                       <button onClick={handleClick} className='beautiful-button'>commit List </button>
                        {click ?
                        (<CommitContent 
                        key={index}
                        item = {item}
                        />): null 
                        }
            
                </div>
            ))}
      </div>
    );
}

// <h3 key={index}>{item.name} {item.author}</h3>

{/* <ul id="nameList">
            {data.length === 0 ? null : data.map((conceptItem) => (
            <CommitData  key={conceptItem.sha1} commitContent={conceptItem.name} commitSh1 ={conceptItem.sha1} commitData ={conceptItem.date} commitAuthor = {conceptItem.author} />
            ))}
            
            </ul> */}