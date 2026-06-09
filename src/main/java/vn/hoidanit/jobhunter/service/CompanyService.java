package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.repository.CompanyRepository;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.domain.dto.Meta;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public ResultPaginationDTO fetchAllCompany(Specification<Company> pabgeable) {
        List<Company> pageCompany = this.companyRepository.findAll(pabgeable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        Meta mt = new Meta();

        // mt.setPage(pageCompany.getNumber() + 1);
        // mt.setPageSize(pageCompany.getSize());

        // mt.setPages(pageCompany.getTotalPages());
        // mt.setTotal(pageCompany.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pageCompany);

        return rs;
    }

    public void handleDeleteCompany(long id) {
        this.companyRepository.deleteById(id);
    }

    public Company handleUpdateCompany(Company company) {
        Optional<Company> companyOptional = this.companyRepository.findById(company.getId());
        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();
            currentCompany.setName(company.getName());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setAddress(company.getAddress());
            currentCompany.setLogo(company.getLogo());
            return this.companyRepository.save(currentCompany);
        } else
            return null;
    }

}
