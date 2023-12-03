import  { useState, useEffect } from 'react';
import CommitContent from './CommitContent.jsx';

export default function CommitComponent({name,userPassword,path}) {
    
    const [data, setData] = useState([]);
    const [click, setClick] = useState(false);
    
    const handleClick = () => {
        setClick(!click);
      };

    

    useEffect(() => {
    
      const dataDTO = {
        nameOfRepo: name, 
        path: path, 
        userPassword: userPassword.password
      }
        async function fetchData() {
        
            try {   
            const url = "http://localhost:8080/AvGit/getAllCommitRepo";
      
            const response = await fetch(url, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body:JSON.stringify(dataDTO)});
      
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
      
            const responseData = await response.json();
            console.log('Response from http://localhost:8080/AvGit/getAllCommitRepo', responseData);
            setData(responseData);
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        }
        fetchData();
      }, []);

      //getting from the server list of commit
    
    return (
        <div >
          <button onClick={handleClick} className='more-button'>commit List </button>
            {data.length === 0 ? null : data.map((item,index) => (
                <div  key={index}>
                        {click ?
                        (<CommitContent 
                        key={index}
                        item = {item}
                        userPassword={userPassword}
                        />): null 
                        }
            
                </div>
            ))}
      </div>
    );
}
