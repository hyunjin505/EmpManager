import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empmanager {

    private List<Employee> employees = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void start() {
        System.out.println("직원 관리 프로그램 시작");

        while (true) {
            showMenu();
            try{
            String input = scanner.nextLine();
            if(input.trim().isEmpty()){
                throw new NumberFormatException("빈값은 허용하지 않습니다.");
            }
            int choice = Integer.parseInt(input);
            scanner.nextLine();

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        listEmployee();
                        break;
                    case 3:
                        increaseSalary();
                        break;
                    case 4:
                        System.out.println("시스템을 종료합니다.");
                        running = false;
                        continue;
//                    case 5:
//                        deleteEmp();
//                        break;
//                    case 6:
//                        System.out.println("시스템을 종료합니다.");
//                        return;
                    default:
                        System.out.println("다시 입력 하시오.");
                }

            }catch (MyException e) {
                System.out.println(e.getMessage());
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void showMenu() {
        //15버전 이상
        System.out.println("""
                \n Menu :
                1.직원 등록
                2.직원 목록 보기
                3.실적 등록
                4.직원 검색
                5.직원 삭제
                6.종료
                메뉴를 선택하시오.
                """);


    }

    void addEmployee() {
        System.out.println("직원 등록");
        System.out.println("""
                등록 직원 종류 선택 :
                1.관리자
                2.개발자
                """);
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("이름 : ");
        String name = scanner.nextLine();
        System.out.print("ID : ");
        int id = scanner.nextInt();
        System.out.print("급여 : ");
        int salary = scanner.nextInt();
        if (type == 1) {
            System.out.print("팀 규모? ");
            int teamSize = scanner.nextInt();
            employees.add(new Manager(name, id, salary, teamSize));

        } else if (type == 2) {
            System.out.print("주 사용 언어? ");
            scanner.nextLine();     //기존 입력값 지우기
            String language = scanner.nextLine();
            employees.add(new Developer(name, id, salary, language));
        } else {
            System.out.println("잘못된 선택입니다.");
        }

    }

    void listEmployee() {
        System.out.println("직원 목록 보기");
        System.out.println("===================================================");
        System.out.println("ID          이름            급여         팀규모");
        System.out.println("===================================================");

        if (employees.isEmpty()) {
            System.out.println("등록된 직원이 없습니다.");
        } else {
            for (Employee emp : employees) {
                emp.introduce();
            }
        }
    }

    void increaseSalary() throws MyException {
        System.out.println("급여 인상 등록");
        System.out.println("직원 ID : ");
        int id = scanner.nextInt();
        Employee target = null;
        for (Employee emp : employees) {
            if (emp.id == id) {
                target = emp;
                break;
            }
        }
        if (target != null) {
            System.out.println("""
                    1.고정값으로 증가
                    2.퍼센트로 증가
                    방식을 선택 :
                    """);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("증가 급여 입력 : ");
                int amount = scanner.nextInt();
                target.increaseSalary(amount);
                System.out.println("급여 증가 성공");
            } else if (choice == 2) {
                System.out.println("증가 급여 퍼센트 입력 : ");
                double percentage = scanner.nextDouble();
                target.increaseSalary(percentage, true);
                System.out.println("급여 증가 퍼센트 적용");
            } else {
                System.out.println("다시 입력 하시오.");
            }
        } else {
            System.out.println("찾는 직원이 없습니다.");
        }
    }

    void searchEmp() {
        System.out.println("=====직원 검색=====");
        System.out.println("검색할 이름 : ");
        String searchName = scanner.nextLine();
        boolean found = false;
        System.out.println("==================================");
        System.out.println("이름      ID     급여    팀규모");
        for (Employee emp : employees) {
            if (emp.getName().contains(searchName)) {
                if (emp instanceof Manager) {
                    Manager mag = (Manager) emp;
                    System.out.printf("%s    %s    %s    %s\n",
                            emp.getName(),
                            emp.getId(),
                            emp.getSalary(),
                            mag.getTeamSize());
                    found = true;
                    System.out.println("==================================");
                }
            }
            if (!found) {
                System.out.println("검색 결과가 없음");
            }
        }
    }

    void deleteEmp() {
        System.out.println("=====직원 삭제=====");
        System.out.println("삭제할 직원의 이름 : ");
        String searchName = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(searchName)) {
                employees.remove(i);
                System.out.println("직원이 삭제 되었습니다.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("해당 이름의 직원을 찾을 수 없음");
        }
    }
}
