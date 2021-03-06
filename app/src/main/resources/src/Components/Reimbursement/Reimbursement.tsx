import React, {useState, useEffect} from 'react';
import './Reimbursement.css';
import defaultImage from "../../userIcon.jpg";
import { useDispatch } from 'react-redux';
import { AppDispatch } from '../../Store';
import { approveReimbursementRequest, denyReimbursementRequest } from '../../Slices/ReimbursementSlice';

export const Reimbursement = (post: any) => {

    const dispatch: AppDispatch = useDispatch();

    const [userDetails, setUserDetails] = useState({
        role:0
    });

    useEffect(() => {
        if (localStorage.getItem('userDetails')) {
            const items = localStorage.getItem('userDetails');
            if (items) {
                setUserDetails(JSON.parse(items));
            }
        }
    }, []);

    const approveRequest = (event: any) => {
        dispatch(approveReimbursementRequest(Number(event.target.value)));
    }

    const denyRequest = (event: any) => {
        dispatch(denyReimbursementRequest(Number(event.target.value)));
    }

    return(
        <div className="post">
            <div className="post-profile">
                <img className="post-image" src={defaultImage} />
                <h3 className="post-user">{post.author} </h3>
            </div>

            <div className="post-content">
                <p>{post.description}</p>
                <p>{post.amount}</p>
                <p>{post.status}</p>
            </div>
            {userDetails.role === 2 ?<div><button className="approve-deny-btn"value={post.reimbursement_id} onClick={approveRequest}>Approve</button><button className="approve-deny-btn" onClick={denyRequest}>Deny</button></div> : <></>}
        </div>
    )

}