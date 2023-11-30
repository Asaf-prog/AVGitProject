import { useState } from 'react';
import './MenuBar.css'; 
export default function MenuBar() {
    
    const [path, setPath] = useState('');
    const [nameOfRepo, setNameOfRepo] = useState('');
  
    const handlePathChange = (e) => {
      setPath(e.target.value);
    };
  
    const handleNameOfRepoChange = (e) => {
      setNameOfRepo(e.target.value);
    };
  
    const handleFormSubmit = (e) => {
      e.preventDefault();
      // You can handle the form submission logic here
      console.log('Submitted values:', { path, nameOfRepo });
      // You may want to reset the form fields after submission
      setPath('');
      setNameOfRepo('');
    };
  
    return (
      <div className="menu-bar-container">
        <div className="menu-bar-box">
          <form onSubmit={handleFormSubmit}>
            <label>
              Path:
              <input
                type="text"
                value={path}
                onChange={handlePathChange}
                placeholder="Enter folder path"
              />
            </label>
            <label>
              Name of Repository:
              <input type="text" value={nameOfRepo} onChange={handleNameOfRepoChange} />
            </label>
            <button type="submit">Submit</button>
          </form>
        </div>
      </div>
    );
}