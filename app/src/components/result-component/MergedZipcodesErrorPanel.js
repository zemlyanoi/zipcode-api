import React, {Component} from 'react';
import './merged-zipcodes-error-component.css'


export default class MergedZipcodesErrorPanel extends Component{

  render() {
    const { mergedZipCodesError } = this.props;

    const style = {
      display: mergedZipCodesError ? "" : "none"
    };

    return (
        <div className="card text-white bg-danger mb-3 error-panel" style={ style }>
          <div className="card-header">Header</div>
          <div className="card-body">
            <h4 className="card-title">Error</h4>
            <p className="card-text">{ mergedZipCodesError }</p>
          </div>
        </div>
    );
  }
};
