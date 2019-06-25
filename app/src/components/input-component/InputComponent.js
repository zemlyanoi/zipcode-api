import React, {Component} from 'react';
import './input-component.css'

export default class InputPanel extends Component{

  render() {
    const { onZipEntered } = this.props;

    return (
        <div className="form-group">
          <label className="col-form-label" htmlFor="inputDefault">Please provide zip code ranges for merging:</label>
          <input type="text" className="form-control" placeholder="[94133,94133] [94200,94299] [94600,94699]"
                 id="inputDefault" onBlur={ onZipEntered }/>
        </div>
    );
  }
};
