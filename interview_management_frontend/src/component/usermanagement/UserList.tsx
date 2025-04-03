import React, { useState, useEffect } from 'react';
import {
  Card,
  CardBody,
  CardTitle,
  Row,
  Col,
  Form,
  FormGroup,
  Label,
  Input,
  Button
} from 'reactstrap';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const UserDetail = () => {
  const { id } = useParams(); // Lấy userId từ URL
  const [userDetail, setUserDetail] = useState({
    username: '',
    email: '',
    phone: '',
    firstName: '',
    lastName: '',
    address: '',
    settingName: '',
    isActive: ''
  });

  // Lấy token từ localStorage (hoặc từ nguồn lưu trữ bạn đang sử dụng)
  const token = localStorage.getItem('token');

  // Gọi API để lấy chi tiết người dùng
  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/admin/${id}`, {
      headers: {
        Authorization: `Bearer ${token}` // Thêm token vào headers
      }
    })
      .then(response => {
        const data = response.data;
        setUserDetail({
          username: data.username || '',
          email: data.email || '',
          phone: data.phone || '',
          firstName: data.firstName || '',
          lastName: data.lastName || '',
          address: data.address || '',
          settingName: data.settingName || 'SELLER',
          isActive: data.isActive === '1' ? 'Active' : 'Inactive'
        });
      })
      .catch(error => {
        console.error('Error fetching user details:', error);
      });
  }, [id, token]);

  return (
    <div>
      <Row>
        <Col md="12">
          <Card>
            <CardTitle tag="h4" className="border-bottom bg-primary p-3 mb-0 text-white">
              User Detail
            </CardTitle>
            <CardBody>
              <Form>
                <Row>
                  <Col md="6">
                    <FormGroup>
                      <Label>Username</Label>
                      <Input type="text" value={userDetail.username} readOnly />
                    </FormGroup>
                  </Col>
                  <Col md="6">
                    <FormGroup>
                      <Label>Email</Label>
                      <Input type="email" value={userDetail.email} readOnly />
                    </FormGroup>
                  </Col>
                </Row>
                <Row>
                  <Col md="6">
                    <FormGroup>
                      <Label>Phone</Label>
                      <Input type="text" value={userDetail.phone} readOnly />
                    </FormGroup>
                  </Col>
                  <Col md="6">
                    <FormGroup>
                      <Label>First Name</Label>
                      <Input type="text" value={userDetail.firstName} />
                    </FormGroup>
                  </Col>
                </Row>
                <Row>
                  <Col md="6">
                    <FormGroup>
                      <Label>Last Name</Label>
                      <Input type="text" value={userDetail.lastName} />
                    </FormGroup>
                  </Col>
                  <Col md="6">
                    <FormGroup>
                      <Label>Setting Name (Role)</Label>
                      <Input type="select" value={userDetail.settingName}>
                        <option>SELLER</option>
                        <option>BUYER</option>
                        <option>ADMIN</option>
                      </Input>
                    </FormGroup>
                  </Col>
                </Row>
                <Row>
                  <Col md="6">
                    <FormGroup>
                      <Label>Address</Label>
                      <Input type="text" value={userDetail.address} />
                    </FormGroup>
                  </Col>
                  <Col md="6">
                    <FormGroup>
                      <Label>Status (isActive)</Label>
                      <Input type="select" value={userDetail.isActive}>
                        <option>Active</option>
                        <option>Inactive</option>
                      </Input>
                    </FormGroup>
                  </Col>
                </Row>
              </Form>
            </CardBody>
            <CardBody className="border-top gap-2 d-flex align-items-center">
              <Button type="submit" className="btn btn-success">
                Save
              </Button>
              <Button type="button" className="btn btn-dark ml-2">
                Cancel
              </Button>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default UserDetail;
