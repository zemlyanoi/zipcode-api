import React, {Component} from 'react';
import InformationPanel from '../information'
import InputPanel from '../input-component'
import MergeZipcodeButton from '../buttons'

import './app.css';
import MergedZipcodesPanel from "../result-component/MergedZipcodesPanel";
import MergedZipcodesErrorPanel from "../result-component/MergedZipcodesErrorPanel";


export default class App extends Component {

    state = {
        zipcode: "",
        response: "",
        error: ""
    };

    onZipEntered = (e) => {
        const value =  e.target.value;
        const oldResponse = value ? this.state.response : "";
        this.setState(() => {
            return {
                zipcode: value,
                response: oldResponse,
                error: ""
            }
        })
    }

    onResponseOk = (e) => {
        const  {zipCodeRanges, error}  = e;
        const oldZipcode = this.state.zipcode;
        this.setState(() => {
            return {
                zipcode: oldZipcode,
                response: zipCodeRanges,
                error: error
            }
        })
    }

    handleMerge = (e) => {
        e.preventDefault()
        const input = this.state.zipcode;
        console.log("ZIP ===== " + this.state.zipcode);
        fetch("http://localhost:8080/process-zipcodes", {
            method: 'POST',
            body: input,
            headers:{
                'Access-Control-Allow-Origin': ''
            }
        }).then(res => res.json())
        .then(this.onResponseOk)
        .catch(res => console.log(res));
    }

    render() {
        return (
            <div className="application">
                <InformationPanel/>
                <InputPanel onZipEntered={ this.onZipEntered }/>
                <MergeZipcodeButton handleMerge={ this.handleMerge } />
                <MergedZipcodesPanel mergedZipCodes={ this.state.response }/>
                <MergedZipcodesErrorPanel mergedZipCodesError={ this.state.error }/>
            </div>
        )
    };

};
