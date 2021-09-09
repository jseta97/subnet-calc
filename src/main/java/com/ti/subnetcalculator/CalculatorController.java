package com.ti.subnetcalculator;

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class CalculatorController {

    @GetMapping
    public String calculatorForm(Model model){
        model.addAttribute("addressData", new AddressData());
        return "calculator";
    }

    @PostMapping
    public String calculatorSubmit(@ModelAttribute AddressData addressData, Model model){
        List<String> results = calculate(addressData);
        model.addAttribute("results", results);
        return "calculator";
    }

    private List<String> calculate(AddressData addressData){
        List<String> results = new ArrayList<>();
        IPAddressString addrString = new IPAddressString(addressData.getAddressWithMask());
        IPAddress addr = addrString.getAddress();
        IPAddress.IPVersion version = addrString.getIPVersion();
        BigInteger size = addr.getCount();
        Integer prefixLen = addr.getNetworkPrefixLength();
        IPAddress mask = addr.getNetworkMask();
        IPAddress networkAddr = addr.mask(mask);
        IPAddress maskNoPrefix = mask.removePrefixLength();
        List<String> networks = getNetworks(networkAddr);
        results.add(version + " address: " + addr);
        results.add(version + " full address: " + addr.toFullString());
        results.add("prefix length: " + prefixLen);
        results.add("total IP addresses: " + size);
        results.add("mask with prefix: " + mask);
        results.add("mask: " + maskNoPrefix);
        results.add("previous address: " + networks.get(0) + "\n");
        results.add("network address: " + networks.get(1) + "\n");
        results.add("next address: " + networks.get(2) + "\n");
        results.add("network range: " + "\n");
        results.add("low: " + addr.getLower() + "\n");
        results.add("up: " + addr.getUpper() + "\n");

        return results;
    }

    private List<String> getNetworks(IPAddress networkAddr) {
        List<String> networks = new ArrayList<>();
        String prev="", actual="", next="";
        BigInteger value;
        BigInteger zero = new BigInteger("0");
        int sectionToChange=7;
        for(int i=7; i>=0; i--){
            value = networkAddr.getDivision(i).getValue();
            if(value.compareTo(zero)!=0){
                sectionToChange = i;
                break;
            }
        }
        for(int i=0; i<=7; i++){
            if(i<sectionToChange){
                prev+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue())+":";
                actual+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue())+":";
                next+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue())+":";
            }else if(i==sectionToChange){
                prev+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue()-1)+":";
                actual+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue())+":";
                next+=Integer.toHexString(networkAddr.getDivision(i).getValue().intValue()+1)+":";
            }else{
                prev+=":";
                actual+=":";
                next+=":";
                break;
            }
        }
        List<Integer> indexesOfDifferences = indexesOfDifferences(prev, next);
        networks.add(prev);
        networks.add(actual);
        networks.add(next);
        for(int i =0; i<3; i++){
            networks.set(i,insertString(networks.get(i)," ", indexesOfDifferences.get(0)-1));
            networks.set(i,insertString(networks.get(i)," ", indexesOfDifferences.get(indexesOfDifferences.size()-1)+1));
        }

        return networks;
    }

    private String insertString(String originalString, String stringToBeInserted, int index)
    {
        String newString = "";
        for (int i = 0; i < originalString.length(); i++) {
            newString += originalString.charAt(i);
            if (i == index) {
                newString += stringToBeInserted;
            }
        }
        return newString;
    }

    private List<Integer> indexesOfDifferences(String str1, String str2) {
        List<Integer> list = new ArrayList<>();
        int i;
        for (i = 0; i < str1.length() && i < str2.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                list.add(i);
            }
        }
        return list;
    }
}

