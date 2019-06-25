import React, {Component} from 'react';
import './merged-zipcodes-component.css'


export default class MergedZipcodesPanel extends Component{

  render() {
    const { mergedZipCodes } = this.props;

    const style = {
      display: mergedZipCodes ? "" : "none"
    };

    return (
        <div className="card text-white bg-success mb-3 success-panel" style={ style } >
          <div className="card-header">Merged result</div>
          <div className="card-body">
            <h4 className="card-title">Success</h4>
            <p className="card-text">{ mergedZipCodes }</p>
          </div>
        </div>
    );
  }
};
