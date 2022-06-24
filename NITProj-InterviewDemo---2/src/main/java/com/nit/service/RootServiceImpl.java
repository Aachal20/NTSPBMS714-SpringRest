package com.nit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Root;
import com.nit.exception.CustomException;
import com.nit.repository.RootRepository;
import com.nit.response.GlobalResponse;

@Service("rootService")
public class RootServiceImpl {

	@Autowired
	private RootRepository repo;

	public GlobalResponse addData(Root root) {
		repo.save(root);
		return new GlobalResponse("success" , "Data saved successfully" , 200);
	}

	/*public Root  viewDataById(String id) {
		try {
	
			if(repo.existsById(id)) {
				System.out.println("RootServiceImpl.viewDataById()");
				Root opt =  repo.findById(id).get();
	
				if (opt.getIsDeleted().equals(false)) {
					return opt;
				} // if
				else if (opt.getIsDeleted().equals(true)) {
					throw new CustomException("Data is deleted for this ID");
				}
				else {
					throw new CustomException("Data not found");
				}catch(Exception e) {
					throw new CustomException(e.getMessage());
				}
			}*/
			
	public Root viewDataById(String sid) {
		try {

			Optional<Root> opt = repo.findById(sid); // here we directly return student obj

			if (opt.isPresent()) {
				Root root = opt.get();
				if (root.getIsDeleted().equals(false)) {
					return root;
				} // if
				else if (root.getIsDeleted().equals(true)) {
					throw new CustomException("Student is deleted for thid ID");
				} else {
					throw new CustomException("Data not found");
				}

			} //
			else {
				throw new CustomException(" not found");
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


				public List<Root> viewData() {
					return  repo.findAll();
				}

				public GlobalResponse updateRootData(Root root) {
					Optional<Root> opt = repo.findById(root.getId());
					if(opt.isPresent()) {
						repo.save(root);
						return new GlobalResponse("Success" ," Data updated successfully " ,200);
					}
					else {
						throw new CustomException("Data not found for thid ID");
					}


				}
				public GlobalResponse deletedata(String  id) {
					try {
						Optional<Root> data = repo.findById(id);
						if (data.isPresent()) {
							Root root  = data.get();
							if (root.getIsDeleted().equals(false)) {
								root.setIsDeleted(true);

								repo.save(root);
								//	return new GlobalResponse("success ", "data  deleted", 200);
							}
							else {
								return new GlobalResponse("Bad request!!", "Data is  allReady deleted", 400);
							}
						}
					} catch (Exception e) {
						throw new CustomException("Data not found for thid ID");
					}
					return new GlobalResponse("success ", "data  deleted", 200);

				}

			}



