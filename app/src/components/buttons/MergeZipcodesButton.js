import React, {Component} from 'react';

export default class MergeZipcodeButton extends Component{

  render() {
    const { handleMerge } = this.props;

    return (
        <button className="btn btn-outline-secondary" onClick={ handleMerge }>Add Item</button>
    );
  }
};
