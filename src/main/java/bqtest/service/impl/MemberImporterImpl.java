package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {

    @Override
    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                    Member m = new Member();
                    m.setId(line.substring(0, 11));
                    m.setLastName(line.substring(12, 36));
                    m.setFirstName(line.substring(37, 61));
                    m.setAddress(line.substring(62, 91));
                    m.setCity(line.substring(92, 111));
                    m.setState(line.substring(112, 115));
                    m.setZip(line.substring(116, 120));
                    return m;
                }).collect(Collectors.toList());
    }

}
