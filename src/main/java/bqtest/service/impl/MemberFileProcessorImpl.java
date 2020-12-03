package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */
    @Override
    protected MemberImporter getMemberImporter() {
        return new MemberImporterImpl();
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        List<Member> list = new ArrayList();
        for (Member m : membersFromFile) {
            int count = 0;
            for (Member mb : membersFromFile) {
                if (m.getId().equals(mb.getId())
                        && m.getFirstName().equals(mb.getFirstName())
                        && m.getLastName().equals(mb.getLastName())
                        && m.getAddress().equals(mb.getAddress())
                        && m.getZip().equals(mb.getZip())
                        && m.getState().equals(mb.getState())
                        && m.getCity().equals(mb.getCity())) {
                    count++;
                }
            }
            if (count == 1) {
                list.add(m);
            }
        }
        return list;
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        List<String> stateList = new ArrayList();
        //get unique states
        for (Member m : validMembers) {
            System.out.println(m.getState());
            if (!stateList.contains(m.getState())) {
                stateList.add(m.getState());
            }
        }
        //state-member list
        Map<String, List<Member>> map = new HashMap();
        for (String s : stateList) {
            List<Member> stList = new ArrayList();
            for (Member m : validMembers) {
                if (s.equals(m.getState())) {
                    stList.add(m);
                }
            }
            map.put(s, stList);
        }
        return map;
    }

}
